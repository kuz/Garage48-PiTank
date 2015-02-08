# Garage48-PiTank

#### On Start-up
sudo service udhcpd start  
sudo service ssh start  
cd Tank  
python camserver.py  
python tcpserver.py  

#### Configure Raspberry Pi as an Access Point
http://elinux.org/RPI-Wireless-Hotspot

#### Read depth data from Kinect
* https://github.com/xxorde/librekinect -- did not work :( (kernel 3.18)
* https://github.com/openkinect/libfreenect & https://github.com/amiller/libfreenect-goodies
