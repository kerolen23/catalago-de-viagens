#FROM openjdk:12-alpine
#
#ARG PROFILE
#ARG ADDITIONAL_OPTS
#
#ENV PROFILE=${PROFILE}
#ENV ADDITIONAL_OPTS=${ADDITIONAL_OPTS}
#
#WORKDIR /opt/ItineraryDimed
#
#COPY /target/ItineraryDimed*.jar ItineraryDimed-0.0.1-SNAPSHOT.jar
#
#
#
#EXPOSE 8081
#
#CMD java ${ADDITIONAL_OPTS} -jar ItineraryDimed-0.0.1-SNAPSHOT.jar --spring.profiles.active=${PROFILE}


FROM openjdk:12-alpine
MAINTAINER angelicaweiler
COPY /target/projetoLogin*.jar /app/projetoLogin.jar
WORKDIR /app
ENTRYPOINT ["java", "-jar", "projetoLogin.jar"]

EXPOSE 8081
