docker run -d --restart=always --name=prometheus -p 9090:9090 -v prometheus.yml:/etc/prometheus/p.yml  prom/prometheus --config.file=/etc/prometheus/p.yml
