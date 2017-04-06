# Mesoc

Pure Java Mesos client for v1 Operator HTTP API.
Scheduler/Executor HTTP API may be future work. 

## How to rebuild Protos.java

Update .proto files from Mesos repository.

```
$ cd /path/to/mesos/
$ find . -type f | egrep ".proto$" | grep v1 | xargs -J % echo cp  % /path/to/mesoc/src/main/resources
$ cd /path/to/mesoc/src/main/resources
$ protoc --proto_path . --java_out ../java mesos/v1/master/master.proto mesos/v1/mesos.proto
```

## License

All files are distributed under Apache License 2.0.

For .proto files from Mesos repository Apache Foundation hold copyright.

For other codes Kota UENISHI is the author.