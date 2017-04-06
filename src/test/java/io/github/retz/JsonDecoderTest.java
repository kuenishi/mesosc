package io.github.retz;

import com.google.protobuf.util.JsonFormat;
import org.apache.mesos.v1.master.Protos;
import org.junit.Test;

import java.util.List;

public class JsonDecoderTest {

    @Test
    public void jd() throws Exception {
        JsonDecoder decoder = new JsonDecoder();
        {
            Protos.Response res = decoder.decodeResponse("{}");
            if (res.hasGetTasks()){
                List<org.apache.mesos.v1.Protos.Task> tasks = res.getGetTasks().getTasksList();
                System.err.println(tasks.size());
            }
        }
        {
            Protos.Call call = Protos.Call.newBuilder().setType(Protos.Call.Type.GET_TASKS).build();
            String json = decoder.encodeCall(call);
            System.err.println(json);
        }
    }

    @Test
    public void test() throws Exception {
        String json = "{\"type\":\"GET_TASKS\", \"get_tasks\" : {\"tasks\":[]}}";

        // Decode JSON
        JsonFormat.Parser parser = JsonFormat.parser();
        //parser.merge(json, builder);
        Protos.Response.Builder builder = Protos.Response.newBuilder();
        parser.merge(json, builder);
        Protos.Response.GetTasks getTasks = builder.build().getGetTasks();
        System.err.println(json + " >< ");
        for (org.apache.mesos.v1.Protos.Task task : getTasks.getTasksList()) {
            System.err.println("Task>" + task);
        }

        /// Encode request
        org.apache.mesos.v1.master.Protos.Response res;
        Protos.Call call = org.apache.mesos.v1.master.Protos.Call.newBuilder()
                .setType(Protos.Call.Type.GET_TASKS)
                .build();

        JsonFormat.Printer printer = JsonFormat.printer();
        String req = printer.print(call);
        System.err.println(req);

        /// Decode request
        Protos.Call.Builder cbuilder = Protos.Call.newBuilder();
        {
            JsonFormat.Parser cparser = JsonFormat.parser();
            cparser.merge(req, cbuilder);
            System.err.println("cb>" + cbuilder.build());
        }
    }
}
