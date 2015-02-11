//Initial code for "Pi Tank" V1.0
//Set wires according to instrucions
//Caution- Engine speeds are set to '150', which is all dependable
// You must check that engine Ampers are below 2A when You hold them in place
// To ensure safety, You can use values like 'analogWrite(ENA,90);'
// DO NOT SHOOT LAZERS AT ANY LIVING BEING!
 
int ENA=5;//connected to Arduino's port 5(output pwm)
int IN1=2;//connected to Arduino's port 2
int IN2=3;//connected to Arduino's port 3
int ENB=6;//connected to Arduino's port 6(output pwm)
int IN3=4;//connected to Arduino's port 4
int IN4=7;//connected to Arduino's port 7
int LZR=13; //Laser connected to Arduino's port 13
int LZROn=0; //Lazer on/off
char incomingByte = '0'; //For incoming serial data
 
void setup()
{
  pinMode(ENA,OUTPUT);//Set pins to output
  pinMode(ENB,OUTPUT);
  pinMode(IN1,OUTPUT);
  pinMode(IN2,OUTPUT);
  pinMode(IN3,OUTPUT);
  pinMode(IN4,OUTPUT);
  pinMode(LZR,OUTPUT);
  digitalWrite(ENA,LOW);//stop Engine A
  digitalWrite(ENB,LOW);//stop Engine B
  digitalWrite(IN1,LOW); //setting motors directon
  digitalWrite(IN2,HIGH);//setting motors directon
  digitalWrite(IN3,HIGH);//setting motors directon
  digitalWrite(IN4,LOW);//setting motors directon
  digitalWrite(LZR,255); //Turn Laser off by default. 255 == off
  Serial.begin(9600);//opens serial port, sets data rate to 9600 bps, to listen incoming things
  Serial.println(' ');//Just a empty space
}
 
void loop()//Loop that always happens
{
  char inByte = ' ';//Clear listener
 
  if(Serial.available())//Checks if a value comes in
  {
    char inByte = Serial.read();//read the incoming data
 
    if (inByte == '0')//Just stand
    {
      Serial.println("Standing!");//Say out what You're doing
      analogWrite(ENA,0);//start driving motorA
      analogWrite(ENB,0);//start driving motorB  
    }
    if (inByte == '1')//Turn left
    {
      //To be sure, re-set engine directions
      digitalWrite(IN1,LOW);
      digitalWrite(IN2,HIGH);
      digitalWrite(IN3,HIGH);
      digitalWrite(IN4,LOW);
     
      Serial.println("Turning left!");//Say out what You're doing
      analogWrite(ENA,150);
      analogWrite(ENB,0);
    }
    if (inByte == '2')//Turn right
    {
      //To be sure, re-set engine directions
      digitalWrite(IN1,LOW);
      digitalWrite(IN2,HIGH);//setting motorA's directon
      digitalWrite(IN3,HIGH);
      digitalWrite(IN4,LOW);
     
      Serial.println("Turning right!");//Say out what You're doing
      analogWrite(ENB,150);
      analogWrite(ENA,0);
    }
    if (inByte == '3')
    {
      //To be sure, re-set engine directions
      digitalWrite(IN1,LOW);
      digitalWrite(IN2,HIGH);
      digitalWrite(IN3,HIGH);
      digitalWrite(IN4,LOW);
     
      Serial.println("Driving forward!");//Say out what You're doing
      analogWrite(ENA,150);
      analogWrite(ENB,150);
    }
 
    if (inByte == '4')
    {
      //Set the engines to work backwards
      digitalWrite(IN1,HIGH);
      digitalWrite(IN2,LOW);
      digitalWrite(IN3,LOW);
      digitalWrite(IN4,HIGH);
     
      Serial.println("Driving backwards!");//Say out what You're doing
      analogWrite(ENA,150);
      analogWrite(ENB,150);
    }
 
    if (inByte == '9')//Input turns lazer
    {
      if (LZROn == 0)//If lazer is currently turned off
      {
        Serial.println("Lazer On!");//Say out what You're doing
        analogWrite(LZR,0);//Turn lazer on
        LZROn = 1;//Set value that lazer is turned on
      }
      else if (LZROn == 1)//If lazer is currently turned on
      {
        Serial.println("Lazer Off!");//Say out what You're doing
        analogWrite(LZR,255);//Turn lazer off
        LZROn = 0;//Set value that lazer is turned off
      }
    }
  }
  delay(100);//delay for 1/10 of a second
}