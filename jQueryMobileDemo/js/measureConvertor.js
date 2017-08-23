"use strict";

var YARD_TO_METER = 0.9144;
var MILE_TO_METER = 1609.34;
var METER_TO_FOOT = 3.2808;
var INCH_TO_CENTIMETER = 2.54;
var FOOT_TO_INCH = 12;
var METER_TO_CENTIMETER = 100;

var OUNCE_TO_GRAM = 28.3495;
var POUND_TO_OUNCE = 16;
var POUND_TO_KILOGRAM = 0.453592;
var QUARTER_TO_POUND  = 27.777778;

var SQFT_TO_SQMT = 0.09290304;

function clearInputFields(containerId) {
    $('#' + containerId).find("input").val("");
}

function showMsg(msgContainerId, msg) {
    var msgContainer = $('#'+msgContainerId);
    if (msgContainer) {
        msgContainer.text(msg);
        msgContainer.fadeIn(400).delay(3000).fadeOut(400);
    }
}

// Calculations
// Temperature
function celsius2Fahrenheit(cVal) {
    return cVal * 9 / 5 + 32;
}

function fahrenheit2Celsius(fVal) {
    return (Math.round((fVal - 32) * 5 / 9 * 100) /100).toFixed(2);
}

// Length
function centimeter2Inch(cVal) {
    return cVal / INCH_TO_CENTIMETER;
}
function inch2Centimeter(iVal) {
    return iVal * INCH_TO_CENTIMETER;
}

function inch2Foot(iVal) {
    return iVal / FOOT_TO_INCH;
}
function foot2Inch(fVal) {
    return fVal * FOOT_TO_INCH;
}

function foot2Meter(fVal) {
    return fVal / METER_TO_FOOT;
}
function meter2Foot(mVal) {
    return mVal * METER_TO_FOOT;
}

function meter2Yard(mVal) {
    return mVal / YARD_TO_METER;
}
function yard2Meter(yVal) {
    return yVal * YARD_TO_METER;
}

function meter2Mile(mVal) {
    return mVal / MILE_TO_METER;
}
function mile2Meter(mlVal) {
    return mlVal * MILE_TO_METER;
}

// Weight
function gram2Ounce(gVal) {
    return gVal / OUNCE_TO_GRAM;
}
function ounce2Gram(oVal) {
    return oVal * OUNCE_TO_GRAM;
}

function ounce2Pound(oVal) {
    return oVal / POUND_TO_OUNCE
}
function pound2Ounce(pVal) {
    return pVal * POUND_TO_OUNCE;
}

function pound2Kilogram(pVal) {
    return pVal * POUND_TO_KILOGRAM;
}
function kilogram2Pound(kgVal) {
    return kgVal / POUND_TO_KILOGRAM;
}

function pound2Quarter(pVal) {
    return pVal / QUARTER_TO_POUND;
}
function quarter2Pound(qVal) {
    return qVal * QUARTER_TO_POUND;
}

// Volume

// Area
function sqFeet2SqMeter(sqftVal) {
    return sqftVal * SQFT_TO_SQMT;
}
function sqMeter2SqFeet(sqmtVal) {
    return sqmtVal / SQFT_TO_SQMT;
}
