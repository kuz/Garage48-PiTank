### /home/pi

#### Third-party software

`/home/pi/Software` has to have
* compiled `mjpg-streamer` (http://sourceforge.net/projects/mjpg-streamer/)

#### Camera Streamer
These: `camserver.*` are obsolete now, since we emply mjpg-streamer to show the camera feed.  
The scipts `mjpgstreamer.sh` reads camera info file buffer and uses mjpg-streamer to create a web server. Open http://192.168.42.1:8080/stream_simple.html to access the camera feed.

#### TCP Server
`tcpserver.py` listens on the port and sends the command via serial interface to Arduino.  
`tcpclient.py` is an example of how one could send commands to the motors.

