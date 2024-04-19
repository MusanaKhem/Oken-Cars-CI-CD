# Use image Ubuntu 
FROM ubuntu:latest

# Update pacakge and instal Apache
RUN apt-get update && \
    apt-get install -y apache2 && \
    apt-get install -y telnet && \
    apt-get clean && \
    rm -rf /var/lib/apt/lists/*
RUN a2enmod proxy proxy_http 
COPY ./oken-cars.conf /etc/apache2/sites-available
COPY ./oken-cars.conf /etc/apache2/sites-enabled
# Command to start Apache
CMD ["/bin/bash", "-c", "echo '127.0.0.1 oken-cars.oken.lan' >> /etc/hosts && echo 'ServerName oken-cars.oken.lan' >> /etc/apache2/apache2.conf && service apache2 start && tail -f /dev/null"]
#CMD ["apache2ctl", "-D", "FOREGROUND"]


