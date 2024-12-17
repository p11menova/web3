function validateY() {
    element = document.getElementById("inputForm:y");
    y = element.value;
    if (parseFloat(y) > 5 || parseFloat(y) < -3 || isNaN(parseFloat(y))) {
        element.setCustomValidity("please enter a number from -3 to 5 :)");
        element.reportValidity();
        return false;
    } else {
        element.setCustomValidity("");
        element.reportValidity();
        return true;
    }
}