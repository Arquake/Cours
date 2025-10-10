FROM alpine:latest
WORKDIR /app
COPY hello.sh .
RUN apk add curl && apk update
RUN chmod +x hello.sh
CMD ["sh", "hello.sh"]