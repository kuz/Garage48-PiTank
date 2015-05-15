#!/usr/bin/python
'''
	Author: Igor Maculan - n3wtron@gmail.com
	A Simple mjpg stream http server
'''
import cv2
from cv2 import cv
import Image
from BaseHTTPServer import BaseHTTPRequestHandler, HTTPServer
import StringIO
import time
import serial
import picamera
import numpy as np

capture=None
ser = None
 
class CamHandler(BaseHTTPRequestHandler):
	def do_GET(self):

		if self.path.endswith('.mjpg'):
			self.send_response(200)
			self.send_header('Content-type','multipart/x-mixed-replace; boundary=--jpgboundary')
			self.end_headers()
			while True:
				try:
					#rc,img = capture.read()
					#if not rc:
					#	continue
					#imgRGB=cv2.cvtColor(img,cv2.COLOR_BGR2RGB)
					#jpg = Image.fromarray(imgRGB)
					tmpFile = StringIO.StringIO()
					#jpg.save(tmpFile,'JPEG')
					camera.capture(tmpFile, format='jpeg')
					self.wfile.write("--jpgboundary")
					self.send_header('Content-type','image/jpeg')
					self.send_header('Content-length',str(tmpFile.len))
					self.end_headers()
					camera.capture(self.wfile, format='jpeg')
					#jpg.save(self.wfile,'JPEG')
					time.sleep(0.05)
				except KeyboardInterrupt:
					break
			return

		if self.path.find('.html') > 0:
			self.send_response(200)
			self.send_header('Content-type','text/html')
			self.end_headers()
			self.wfile.write('<html><head></head><body background="http://192.168.42.1:8080/cam.mjpg" style="background-repeat: no-repeat; background-size: 100%;">')
			#self.wfile.write('<table width="100%" height="100%" border=0><tr><td><a href="http://192.168.42.1:8080/index.html?left"><img src="http://192.168.42.1:8080/track.png"/><a></td><td width=90%>&nbsp;</td><td><a href="http://192.168.42.1:8080/index.html?right"><img src="http://192.168.42.1:8080/track.png"/><a></td></tr></table>')
			self.wfile.write("""
				
			""")
			self.wfile.write('</body></html>')
			return
 
def main():

	# connect to Arduino
	global ser
	#ser = serial.Serial(port='/dev/ttyACM0', baudrate=9600)

	global capture
	capture = cv2.VideoCapture(0)
	capture.set(cv.CV_CAP_PROP_FPS, 5)
	capture.set(cv.CV_CAP_PROP_FRAME_WIDTH, 320)
	capture.set(cv.CV_CAP_PROP_FRAME_HEIGHT, 240)

	global camera
	#camera = picamera.PiCamera(resolution=(320,240), framerate=10, sensor_mode=6)
	camera = picamera.PiCamera()
	camera.resolution = (320, 240)

	global img
	try:
		server = HTTPServer(('', 8080), CamHandler)
		print "server started"
		server.serve_forever()
	except KeyboardInterrupt:
		capture.release()
		server.socket.close()
 
if __name__ == '__main__':
	main()
