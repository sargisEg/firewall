package org.firewall.service;

import lombok.RequiredArgsConstructor;
import org.firewall.helper.RuleCommandGenerator;
import org.firewall.model.entity.CustomRules;
import org.firewall.model.entity.RuleGroup;
import org.firewall.repository.RuleRepository;
import org.springframework.stereotype.Service;

import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.List;

@Service
@RequiredArgsConstructor
public class RuleServiceImpl implements RuleService {

    private final RuleRepository ruleRepository;

    @Override
    public void updateFirewallRules(CustomRules customRules) throws IOException {
        final StringBuilder config = new StringBuilder("table ip custom\nflush table ip custom\n\ntable ip custom {\n");

        buildInput(config, customRules.getInput());
        buildOutput(config, customRules.getOutput());
        buildForward(config, customRules.getForward());

        final String path = extractNftScriptFileFromResourcesAndGetPath() ;

        final ProcessBuilder processBuilder = new ProcessBuilder(List.of("/bin/bash", path, config.toString()));
        processBuilder.start();

        ruleRepository.save(customRules);
    }

    @Override
    public CustomRules getRules() {
        return ruleRepository.get();
    }

    private void buildInput(StringBuilder config, RuleGroup ruleGroup) {
        config.append("\tchain INPUT {\n").append("\t\ttype filter hook input priority filter;\n");
        ruleGroup.getRules().forEach(rule -> config.append("\t\t\t").append(RuleCommandGenerator.getCommand(rule)).append("\n"));
        config.append("\t\tpolicy ").append(ruleGroup.getPolicyType().getValue()).append(";\n");
        config.append("\t}\n");
    }

    private void buildOutput(StringBuilder config, RuleGroup ruleGroup) {
        config.append("\tchain OUTPUT {\n").append("\t\ttype filter hook output priority filter;\n");
        ruleGroup.getRules().forEach(rule -> config.append("\t\t\t").append(RuleCommandGenerator.getCommand(rule)).append("\n"));
        config.append("\t\tpolicy ").append(ruleGroup.getPolicyType().getValue()).append(";\n");
        config.append("\t}\n");
    }

    private void buildForward(StringBuilder config, RuleGroup ruleGroup) {
        config.append("\tchain FORWARD {\n").append("\t\ttype filter hook forward priority filter;\n");
        ruleGroup.getRules().forEach(rule -> config.append("\t\t\t").append(RuleCommandGenerator.getCommand(rule)).append("\n"));
        config.append("\t\tpolicy ").append(ruleGroup.getPolicyType().getValue()).append(";\n");
        config.append("\t}\n").append("}");
    }

    private String extractNftScriptFileFromResourcesAndGetPath() {
        ClassLoader classLoader = RuleServiceImpl.class.getClassLoader();
        InputStream inputStream = classLoader.getResourceAsStream("/nft/" + "nft.sh");
        String jarPath = RuleServiceImpl.class.getProtectionDomain().getCodeSource().getLocation().getPath();
        jarPath = jarPath.substring(0, jarPath.lastIndexOf(".jar") + 4).replace("file:", "");
        String filePath = jarPath.substring(0, jarPath.lastIndexOf('/') + 1) + "nft.sh";
        try (FileOutputStream outputStream = new FileOutputStream(filePath)) {
            byte[] buffer = new byte[4096];
            int bytesRead;
            while ((bytesRead = inputStream.read(buffer)) != -1) {
                outputStream.write(buffer, 0, bytesRead);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return filePath;
    }
}
