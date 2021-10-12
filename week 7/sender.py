#!/usr/bin/env python3

import socket

HOST = '127.0.0.1'  # Standard loopback interface address (localhost)
PORT = 65432        # Port to listen on (non-privileged ports are > 1023)

### First I creat a socket object
with socket.socket(socket.AF_INET, socket.SOCK_STREAM) as s:
    s.bind((HOST, PORT)) #I gave some property to my server
    s.listen() #I listen the port 65432
    conn, addr = s.accept() #I creat a connection between the client and the server
    with conn:
        print('Client Information :',addr) #I print some information about the client
        while True:
            data = conn.recv(1024) #I wait data from the client
            if not data: #If I received no data, i stop my program
                break
            conn.sendall(data) #If I received  some data from the client, I resend this data


### AF_INET => internet address family
### sock_stream => socket type for tcp
### bind => use in order to associate the socket with
###         a specific network interface and port
