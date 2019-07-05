# Dummy function

Forwards the original message without any processing.

Example request:
```bash
curl http://127.0.0.1:8080/function/dummy -d '{"specversion":"0.2","type":"io.github.ust.mico.result","source":"/router","id":"A234-1234-1234","time":"2019-05-08T17:31:00Z","contentType":"application/json","data":"test"}'
```
