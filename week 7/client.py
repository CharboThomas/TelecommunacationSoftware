#!/usr/bin/env python3

import socket

HOST = '127.0.0.1'  # The server's hostname or IP address
PORT = 65432        # The port used by the server

with socket.socket(socket.AF_INET, socket.SOCK_STREAM) as s:
    s.connect((HOST, PORT))
    s.sendall(b'thomas charbonnet') # I send my name to the host server
    data = s.recv(1024) # I wait for data send by the server


print('Received', repr(data))
