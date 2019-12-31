# Smart-Phone-Infromation

Application A contains two activities and a broadcast receiver. The main
activity contains a welcome message and a button. When the button is pressed, the
activity checks whether app A has obtained dangerous permission
edu.uic.cs478.s19.kaboom. (This permission is defined in app C below.) If A does not
have the permission, it requests it of the user. If the user grants the permission,
processing continues by creating and registering its broadcast receiver
programmatically. Subsequently, A launches the main activity in app B and enters the
stopped state.
The broadcast receiver catches broadcast intents sent by C and launches the second
activity in order to display the image of the smart phone.
The second activity is intended to display the web page of smart phones as requested
by C. The broadcast intent will contain an extra specifying the smart phone whose
page must be displayed. The activity should be destroyed when the user starts a new
activity on top of it. (It is automatically destroyed, when the user presses the “back”
button.)


2. Application B consists of a single activity and a broadcast receiver. The
activity is started by A; however, this activity requires that the A activity have
permission edu.uic.cs478.s19.kaboom. If A does not have the permission, B’s main
activity is not started. Otherwise, the main activity of B displays a welcome message
and a button. When the button is pressed, the activity checks whether B was granted
permission edu.uic.cs478.s19.kaboom. If B does not have the permission, it requests it
in a way similar to A. If the permission is granted, B registers its receiver and then
starts the main activity in application C. If the permission is denied, B displays a toast
message and terminates itself.


3. Application C contains a single activity that consists of two fragments. In
addition C defines permission edu.uic.cs478.s19.kaboom. This application starts when
its main activity receives the intent sent by B, if B has permission
edu.uic.cs478.s19.kaboom. In this case, C’s main activity is displayed with its first
fragment.
App C maintains an action bar. The action bar shows the name of the application and
an icon associated with the application (your choice). The action bar has an options
menu that displays just two menu options: (1) launch applications A and B and (2) exit
C. When the first item is selected, C broadcast a single, ordered intent to start A and
B. Assuming that A and B have the permission, B will first display its toast
message, then A will display the web page of the currently selected smart phone. If no
smart phone is selected yet, no intent is sent and a toast message is displayed instead.
The main activity in C contains two fragments. The first fragment displays a list of
smart phone names. (Each list item consists of a single string.) The device user may
select any point from the list; when this happens, the selected item is highlighted. The
second fragment shows an image of the selected item.

When the device is in portrait mode, the two fragments are displayed on different
screens. First, the device will show only the first fragment. When the user selects an
item, the the first fragment disappears and the second fragment is shown. Pressing the
“back” soft button on the device, will return the device to the original configuration (first
fragment only), thereby allowing the user to select a different smart phone. When the
device is in landscape mode, application C initially shows only the first fragment across
the entire width of the screen. As soon as a user selects an item, the first fragment is
“shrunk” to about 1/3 of the screen’s width. This fragment will appear in the left-hand
side of the screen, with the second fragment taking up the remaining 2/3 of the display
on the right. Again, pressing the “back” button will return the application to its initial
configuration. 




