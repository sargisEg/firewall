document.addEventListener("DOMContentLoaded", function() {
  const expandableHeaders = document.querySelectorAll(".expandable-header");

  expandableHeaders.forEach(header => {
    header.addEventListener("click", function() {
      const expandableItem = this.parentElement;
      expandableItem.classList.toggle("active");
    });
  });
});
