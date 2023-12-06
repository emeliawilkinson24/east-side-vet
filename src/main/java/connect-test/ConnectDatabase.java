import com.datastax.oss.driver.api.core.CqlSession;
import com.datastax.oss.driver.api.core.cql.ResultSet;
import com.datastax.oss.driver.api.core.cql.Row;
import java.nio.file.Paths;

public class ConnectDatabase {

   public static void main(String[] args) {
       // Create the CqlSession object:
       try (CqlSession session = CqlSession.builder()
           .withCloudSecureConnectBundle(Paths.get("/Users/emelia.wilkinson/driver-testing/java/secure-connect-nonvectortestdb.zip"))
           .withAuthCredentials("DAuJHbTyApKuLRuqgroinZZF","cNSum6tHX+17Ygcpyy_Bvo70B-ncxCscqWaywtx+XGF3J-Hy0dEi+h9iQtEmmJD8GQsekaKy7RHkx.gwJlTZsuMxYt-FsU.KD3OvE0Z5G-OhQS7CZH.U.xDMBs_LGhSR")
           .withKeyspace("non_vector")
           .build()) {
           // Select the release_version from the system.local table:
           ResultSet rs = session.execute("select release_version from system.local");
           Row row = rs.one();
           //Print the results of the CQL query to the console:
           if (row != null) {
               System.out.println(row.getString("release_version"));
           } else {
               System.out.println("An error occurred.");
           }
       }
       System.exit(0);
   }
}