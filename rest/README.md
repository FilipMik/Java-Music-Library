# REST API

REST API is available on following url: [http://localhost:8080](http://localhost:8080)

## Commands

### Album

Get all albums:

```
curl -i -X GET http://localhost:8080/PA165/rest/album
```

Find album by ID:

```
curl -i -X GET http://localhost:8080/PA165/rest/album/{id}
```

Find album's artists:

```
curl -i -X GET http://localhost:8080/PA165/rest/album/{id}/artists
```

Find albums by artist name:

```
curl -i -X GET http://localhost:8080/PA165/rest/album/artist/name/{name}
```

With artist's ID find its albums:

```
curl -i -X GET http://localhost:8080/PA165/rest/album/artist/{id}
```

Get all the albums uploaded in the last week:

```
curl -i -X GET http://localhost:8080/PA165/rest/album/lastweek

```
Find album by the title:

```
curl -i -X GET http://localhost:8080/PA165/rest/album/title/{title}
```

Create album:

```
curl -X POST -i -H "Content-Type: application/json" --data ‘{“releaseDate”:”2012-06-06 01:06”,”Title”:”Title“,”commentary”:”Comment”,”albumArtUrl”:”URL”}' http://localhost:8080/PA165/rest/album
```

Update album:

```
curl -X PUT -i -H "Content-Type: application/json" --data ‘{“albumId“:1,“releaseDate”:”2012-06-06 01:06”,”Title”:”Title“,”commentary”:”Comment”,”albumArtUrl”:”URL”}' http://localhost:8080/PA165/rest/album
```

Add multiple songs to album:

```
curl -X POST -i -H "Content-Type: application/json" --data ‘[ 1, 2, 3 ]' http://localhost:8080/PA165/rest/album/{albumId}/song
```

Add one song to album:

```
curl -i -X GET http://localhost:8080/PA165/rest/album/{albumId}/song/{songId}
```

Remove song from album:

```
url -i -X DELETE http://localhost:8080/PA165/rest/album/{albumId}/song/{songId}
```

Delete album:

```
curl -i -X DELETE http://localhost:8080/PA165/rest/album/{id}
```

## Artist

Get all artists:

```
curl -i -X GET http://localhost:8080/PA165/rest/artist
```

Get artist by id:

```
curl -i -X GET http://localhost:8080/PA165/rest/artist/{id}
```

Get artist by name:

```
curl -i -X GET http://localhost:8080/PA165/rest/artist/name/{name}
```

Create artist:

```
curl -X POST -i -H "Content-Type: application/json" --data {"name":"sample name","birthDate":"1990-01-01 05:15", "info":"Some info"} http://localhost:8080/PA165/rest/artist/create
```

Update artist:

```
curl -X POST -i -H "Content-Type: application/json" --data {"id":5,"name":"sample name","birthDate":"1990-01-01 05:15", "info":"Some info"} http://localhost:8080/PA165/rest/artist/
```

Delete artist:

```
curl -i -X DELETE http://localhost:8080/PA165/rest/artist/{id}
```

## Playlist

Get all playlists:

```
curl -i -X GET http://localhost:8080/PA165/rest/playlist
```

Get playlist by id:

```
curl -i -X GET http://localhost:8080/PA165/rest/playlist/{id}
```

Get playlist by title:

```
curl -i -X GET http://localhost:8080/PA165/rest/playlist/title/{title}
```

Get users playlists:

```
curl -i -X GET http://localhost:8080/PA165/rest/playlist/user/{userId}
```

Create playlist:

```
curl -X POST -i -H "Content-Type: application/json" --data {"title":"sample title","dateCreated":"2018-12-15 05:15"} http://localhost:8080/PA165/rest/playlist/create
```

Update playlist:

```
curl -X PUT -i -H "Content-Type: application/json" --data {"id":5,"title":"sample title","dateCreated":"2018-12-15 05:15"} http://localhost:8080/PA165/rest/playlist/
```

Add one song to playlist:

```
curl -X POST -i -H "Content-Type": application/json --data "" http://localhost:8080/PA165/rest/playlist/{playlistId}/song/{songId}
```

Add multiple songs to playlist:
 ```
curl -X POST -i -H "Content-Type: application/json" --data ‘[ 1, 2, 3 ]' http://localhost:8080/PA165/rest/playlist/{playlistId}/song
```

Remove song from playlist:

```
url -i -X DELETE http://localhost:8080/PA165/rest/playlist/{playlistId}/song/{songId}
```

Delete artist:

```
curl -i -X DELETE http://localhost:8080/PA165/rest/playlist/{id}
```

## Song

## User
