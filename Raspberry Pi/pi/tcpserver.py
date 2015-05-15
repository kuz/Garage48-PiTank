import socket
import serial

# TCP / IP settings
TCP_IP = ''
TCP_PORT = 12345

# initialize socket and start listening
s = socket.socket(socket.AF_INET, socket.SOCK_STREAM)
s.bind((TCP_IP, TCP_PORT))
s.listen(5)

# initalize connection to Arduino
ser = serial.Serial(port='/dev/ttyACM0', baudrate=9600)

# wait for commands and send them to Arduino
while True:
	connect, address = s.accept()
	command = (connect.recv(1)).strip()
	print "Got", command
	ser.write(str(command))
	