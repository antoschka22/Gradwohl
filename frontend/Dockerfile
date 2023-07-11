FROM nginx:1.17.1-alpine

COPY nginx.conf /etc/nginx/nginx.conf

RUN rm /usr/share/nginx/html/index.html
RUN rm /usr/share/nginx/html/50x.html

COPY gradwohl_website/dist/gradwohl_website /usr/share/nginx/html