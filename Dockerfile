FROM openjdk:11-jre-slim-buster
MAINTAINER bdvries bdvries@sogyo.nl

WORKDIR /usr/bin/mancala
COPY target/mancala*.jar .

CMD java $(ls mancala*.jar)
