#!/bin/bash
mkdir /tmp/stream
raspistill --nopreview -w 320 -h 240 -vf -q 10 -o /tmp/stream/pic.jpg -tl 10 -t 9999999 -th 0:0:0 &
export LD_LIBRARY_PATH=/home/pi/Software/mjpg-streamer
/home/pi/Software/mjpg-streamer/mjpg_streamer -i "/home/pi/Software/mjpg-streamer/input_file.so -f /tmp/stream -n pic.jpg" -o "/home/pi/Software/mjpg-streamer/output_http.so -w /home/pi/Software/mjpg-streamer/www"
