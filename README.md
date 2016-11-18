# Bug: tooltips not working when displaying PDF in BrowserFrame

## Description
There is an issue with tooltips respectively item descriptions (in a table) when using Microsoft edge.
When a BrowserFrame displaying a PDF, is added to the UI all tooltips stop working . 
This does not happen in firefox or chrome.

Inspecting the DOM gives a first clue about what might be the cause. It seems like when using edge an iframe is attached to the DOM 
right next to the tooltip div. For both elements (iframe and tooltip div) the style attribute get updated when tooltip is displayed
(from "left: -5000px; top: -5000px;" to the current cursor position). In chrome or firefox there is no iframe attached when using
tooltips. As soon as another iframe is added to the DOM (by adding a BrowserFrame) the style attribute for both elements does not 
get updated anymore. It seems like some selector in some JS routine for showing the tooltip is broken when a second 
iframe is attached. Unfortunately there is no error displayed, neither in the console log nor in the vaadin debug window.
