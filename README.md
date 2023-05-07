# Bloodweb Pathfinder
Unofficial companion app for Dead by Daylight, an asymmetric survival-horror game by Behavior Interactive Inc. Developed in Kotlin for Android in partial fulfillment of a course in mobile programming at the University of Arkansas.

## Problem

Since its development, new features added to the original game have caused Bloodweb Pathfinder to be obsolete. The gist of the problem, however, was the need for a player to optimize the amount of in-game currency spent while progressing through the game. This currency was spent on nodes in a web (the _Bloodweb_), and the player could spend less by strategically purchasing nodes such that an in-game "adversary" consumed large segments of the web. Below is an example of one of these webs.

<p align="center">
<img src="https://github.com/jacksonb-cs/Bloodweb-Pathfinder/blob/main/Screenshots/Report%20Example%20Web.png?raw=true" width="400">
</p>

Although the above image is rather busy, the only things of note (in order to judge my project at a glance) are:
- Nodes (shown as white, circled icons)
- Node's background color
- Node's background shape
- Active nodes (circular border and connecting edges are bright tan)
- Inactive nodes (border and edges are dim gray)
- Bought nodes (border and edges are bright red)
- Consumed nodes (border and edges are black)

## Solution

Bloodweb Pathfinder's end goal was to provide the ability to take a picture of a _Bloodweb_ and display the optimal order of node purchases to be performed by the player. As much of this functionality was beyond the scope of a one-semester course on Android app development, the merit of Bloodweb Pathfinder lies in its modular architecture. The application's architecture utilizes the Model-View-Viewmodel (MVVM) approach, and care was taken to enable further development to seamlessly plug in features such as image segmentation on a remote server and intelligent graph traversal. The project was also an opportunity to learn Android Data Binding (albeit under a strict time constraint).

## Result

### Populated Webs

<p float="left" align="center">
	<img src="https://github.com/jacksonb-cs/Bloodweb-Pathfinder/blob/main/Screenshots/med_web_original.png?raw=true" height="400">
	<img src="https://github.com/jacksonb-cs/Bloodweb-Pathfinder/blob/main/Screenshots/med_web_ui.png?raw=true" height="600">
</p>

<p float="left" align="center">
	<img src="https://github.com/jacksonb-cs/Bloodweb-Pathfinder/blob/main/Screenshots/big_web_original.png?raw=true" height="400">
	<img src="https://github.com/jacksonb-cs/Bloodweb-Pathfinder/blob/main/Screenshots/big_web_ui.png?raw=true" height="600">
</p>

### Use Flow

<p float="left" align="center">
	<img src="https://github.com/jacksonb-cs/Bloodweb-Pathfinder/blob/main/Screenshots/blank.png?raw=true" height="600">
	<img src="https://github.com/jacksonb-cs/Bloodweb-Pathfinder/blob/main/Screenshots/med_web_ui.png?raw=true" height="600">
	<img src="https://github.com/jacksonb-cs/Bloodweb-Pathfinder/blob/main/Screenshots/med_filled_web.png?raw=true" height="600">
</p>
