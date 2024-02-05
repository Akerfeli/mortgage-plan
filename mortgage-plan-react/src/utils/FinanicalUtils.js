
// Moves decimal point two steps to the left
export const moveDecimalLeft = (number) => {
    // Convert the number to a string
    let numberString = number.toString();

    // If the number is less than 10, add a leading zero
    if (number < 10) {
        numberString = "0" + numberString;
    }

    // If the number is less than 100, add a leading zero
    if (number < 100) {
        numberString = "0" + numberString;
    }

    // Insert a dot before the second last digit
    numberString = numberString.slice(0, -2) + "." + numberString.slice(-2);

    // Remove trailing zeros
    numberString = numberString.replace(/\.?0+$/, "");

    // Remove trailing dot
    numberString = numberString.replace(/\.$/, "");

    return numberString;
};


// Move decimal two steps to the right
export const moveDecimalRight = (value) => {

    // Convert value to string if it's a number
    value = typeof value === 'number' ? value.toString() : value;

    // If there are more than two symbols after the decimal point, throw an error
    if (value.includes(".") && value.split(".")[1].length > 2) {
        throw new Error("Invalid value, more than two decimals: " + value);
    }

    // First normalize the value, so it has two decimal places
    let normalizedValue = normalizeDecimalString(value);

    // Remove the dot and convert to cents
    return normalizedValue.replace(".", "");
};


const normalizeDecimalString = (value) => {
    if (!value.includes(".")) {
        // Add ".00" if decimal is not present
        return value + ".00";
    }

    // Add a zero after the decimal if there is only one digit after it
    if (value.match(".*\\.\\d$")) {
        return value + "0";
    }
    return value;
}