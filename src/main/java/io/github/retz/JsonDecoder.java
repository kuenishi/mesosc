package io.github.retz;

import com.google.protobuf.InvalidProtocolBufferException;
import com.google.protobuf.util.JsonFormat;
import org.apache.mesos.v1.master.Protos;

public class JsonDecoder {
    JsonFormat.Parser parser;
    JsonFormat.Printer printer;
    public JsonDecoder() {
        parser = JsonFormat.parser();
        printer = JsonFormat.printer();
    }

    public Protos.Response decodeResponse(String json) throws InvalidProtocolBufferException {
        Protos.Response.Builder builder = Protos.Response.newBuilder();
        parser.merge(json, builder);
        return builder.build();
    }

    public String encodeCall(Protos.Call call) throws InvalidProtocolBufferException {
        return printer.print(call);
    }
}
