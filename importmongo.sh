docker run -it --link mongoserver:mongo --rm -v "`pwd`":/data mongo bash
mongoimport --db cine --collection movies --host mongo --port 27017 --file /data/movies.json --jsonArray
mongoimport --db cine --collection artists --host mongo --port 27017 --file /data/artits.json --jsonArray
