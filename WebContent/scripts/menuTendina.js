
document.addEventListener("DOMContentLoaded", function () {
const menuButton = document.getElementById("menuIn");
const dropdown = document.getElementById("dropdown-menu");

menuButton.addEventListener("click", function (e) {
	e.preventDefault();
	e.stopPropagation();

	dropdown.style.display = dropdown.style.display === "block" ? "none" : "block";
});

	document.addEventListener("click", function (e) {
		if (!dropdown.contains(e.target) && e.target.id !== "menuIn") {
			dropdown.style.display = "none";
		}
	});
});
