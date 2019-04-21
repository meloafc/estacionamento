FROM maven:3.6-jdk-8-alpine
RUN apk update
RUN apk upgrade
RUN apk add ca-certificates && update-ca-certificates
RUN apk add --update tzdata
ENV TZ=America/Manaus
RUN rm -rf /var/cache/apk/*