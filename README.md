# chainwallet


###### 安装MySql

<pre><code>

	$ docker run --name demomysql \
    		--restart=always \
    		-p 3306:3306 \
    		-v /xxx/Local-MysqlDB/var/lib/mysql:/var/lib/mysql \
    		-e MYSQL_ROOT_PASSWORD=micerw1234 \
    		-d mysql:latest
</code></pre>

