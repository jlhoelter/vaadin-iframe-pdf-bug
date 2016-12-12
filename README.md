# Bug: tooltips not working when displaying PDF in BrowserFrame

## Description
There is an issue with tooltips respectively item descriptions (in a table) when using Microsoft IE 11.
When a BrowserFrame displaying a PDF is added to the UI all tooltips stop working . 
This does not happen in firefox or chrome.

Inspecting the DOM gives a first clue about what might be the cause. It seems like when using IE 11 an iframe is attached to the DOM right next to the tooltip div. For both elements (iframe and tooltip div) the style attribute get updated when tooltip is displayed (from "left: -5000px; top: -5000px;" to the current cursor position). In chrome or firefox there is no iframe attached when using tooltips. As soon as another iframe is added to the DOM (by adding a BrowserFrame) the style attribute for both elements does not get updated anymore. It seems like some selector in some JS routine for showing the tooltip is broken when a second iframe is attached. Unfortunately there is no error displayed, neither in the console log nor in the vaadin debug window.

## Steps to reproduce
1. Start the Spring Boot App
2. Open the Microsoft IE 11 and go to http://localhost:8080
3. A PDF (book of vaadin) should be displayed
4. Hover over the label -> no tooltip is displayed
5. Use the checkbox to hide the PDF Viewer
6. Hover over the label ->the tooltip is now displayed
