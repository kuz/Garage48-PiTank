### Scripts for the OS

`/etc/rc.local` will start the following components when the system starts:
* DCHP Server
* MJPG Streamer to send camera feed to web (http://192.168.42.1:8080/stream_simple.html)
* TCP Server to relay commands from the controller to Arduino
