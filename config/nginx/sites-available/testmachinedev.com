server {
	listen 80;
	server_name testmachinedev.com;

	root /vagrant/config;
        index index.html index.htm test.html;

	location /sample {
        	proxy_pass  http://192.168.33.33:8080/Sample/;
    	}	
}

server {
        listen 443 ssl;
        server_name testmachinedev.com;

        root /vagrant/config;
        index index.html index.htm test.html;

        ssl on;
        ssl_certificate /etc/nginx/ssl/testmachinedev.com/testmachinedev.com.crt;
        ssl_certificate_key /etc/nginx/ssl/testmachinedev.com/testmachinedev.com.key;

	location /catalog {
                proxy_pass  http://192.168.33.33:8080/catalog/;
        }

	location /certificado {
		proxy_pass https://192.168.33.33:8443/TesteCertificadoJava/;
	}
}
