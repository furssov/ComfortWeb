document.addEventListener("DOMContentLoaded", function() {
    var cardImages = document.querySelectorAll(".card-img-top");
    var cardButtons = document.querySelectorAll(".card-button");
    var fullscreenImages = document.querySelectorAll(".fullscreen-image");
    var closeButtons = document.querySelectorAll(".close-button");

    cardButtons.forEach(function(button, index) {
        button.addEventListener("click", function() {
            fullscreenImages[index].classList.add("active");
        });
    });

    closeButtons.forEach(function(button) {
        button.addEventListener("click", function() {
            var fullscreenImage = button.closest(".fullscreen-image");
            fullscreenImage.classList.remove("active");
        });
    });
});
