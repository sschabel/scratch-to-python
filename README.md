# Scratch to Python

We'll see how far this goes. I am starting on this because I can't seem to find any good converter for Scratch 3 files to Python.

## Why would I want to do this?

For my kids. I'd like to be able to program a simple Raspberry Pi based robot with them and use Scratch to team them how to program.

However, the only support I found for GPIO pins is the OneGPIO extension for Scratch. This, it appears, still requires your Scratch code editor to be attached to the robot. So... this would work for something like a RPI 4 or 5, but I wanted to be cheap and use a Raspberry Pi Pico. So that means I either need to attach a USB cable from the RPI Pico to my computer... **or** **create** **a** **Scratch** **to** **MicroPython** **converter.**

## So why Spring Shell and Spring Boot?

Because it's what I know and easy to get started quickly. I also am somewhat familiar with the Jackson library for reading JSON. However, I may choose to change this to something else in the future once I get an MVP up and running. But, for now, this is just a small hobby project to help do something fun with my kiddos.

## To-do List
- [ ] Validate file that user passes is a .sb3 (Scratch 3 format) file
- [ ] Unzip .sb3 file and process the Project.json file
- [ ] Process the Project.json file and convert to Python (the hard part!)
- [ ] Create a new Python file with the proper code
- [ ] Print the proper message(s) to let the user know what file was created and any other pertinent info
- [ ] Is there any other things I can automate to make it easier to get the MicroPython onto a Raspberry Pi Pico?
- [ ] Create a better user interface (hey, why not if I got the time?!)

