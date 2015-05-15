import socket
import time

TCP_IP = '127.0.0.1'
TCP_PORT = 12345

rep = 50
sequence = [1] * rep + [0] + [2] * rep + [0] + [3] * rep + [0]

for c in sequence:	
	s = socket.socket(socket.AF_INET, socket.SOCK_STREAM)
	s.connect((TCP_IP, TCP_PORT))
	s.send(str(chr(48 + c)))
	time.sleep(0.1)