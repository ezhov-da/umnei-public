FROM openjdk:8
RUN apt-get update && apt-get install -y \
    xvfb \
    x11vnc \
    libxext6 \
    libxrender1 \
    libxtst6
RUN mkdir /opt/umnei
COPY ./application/target /opt/umnei
WORKDIR /opt/umnei

#решение проблемы с работой AWT java.awt.AWTError: Can't connect to X11 window server using ':0.0' as the value of the DISPLAY variable.
#http://elementalselenium.com/tips/38-headless

CMD ["sh", "-c", "xvfb-run java -Xmx2048m -Xms150m -Djava.awt.headless=false -jar /opt/umnei/application.jar"]