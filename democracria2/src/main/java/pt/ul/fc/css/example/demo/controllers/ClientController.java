package pt.ul.fc.css.example.demo.controllers;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ListView;
import javafx.stage.Stage;

public class ClientController {

  @FXML private ListView<String> listView1;

  @FXML private ListView<String> listView2;

  @FXML private ListView<String> listView3;

  @FXML private ListView<String> listView4;

  @FXML private ListView<String> listView5;

  @FXML private ListView<String> listView6;

  @FXML private ListView<String> listView7;

  @FXML private ListView<String> listView8;

  @FXML private ListView<String> cPlistView1;

  @FXML private ListView<String> cPlistView2;

  @FXML private ListView<String> cPlistView3;

  @FXML private ListView<String> cPlistView4;

  @FXML private ListView<String> cPlistView5;

  @FXML private ListView<String> cPlistView6;

  @FXML private ListView<String> cPlistView7;

  @FXML private ListView<String> cPlistView8;

  @FXML private ListView<String> cPlistView9;

  @FXML private ListView<String> cPlistView10;

  @FXML private ListView<String> cPlistView11;

  @FXML
  private void initialize() {}

  private void loaddata() {
    try {
      String apiUrl = "http://localhost:8080/votacoesEmCurso";

      // Send the REST request
      URL url = new URL(apiUrl);
      HttpURLConnection connection = (HttpURLConnection) url.openConnection();
      connection.setRequestMethod("GET");

      // Read the response
      BufferedReader reader =
          new BufferedReader(new InputStreamReader(connection.getInputStream()));
      StringBuilder response = new StringBuilder();
      String line;
      while ((line = reader.readLine()) != null) {
        response.append(line);
      }
      reader.close();

      String responseData = response.toString();
      System.out.println(responseData);

      ObservableList<String> iD = FXCollections.observableArrayList();
      ObservableList<String> titulo = FXCollections.observableArrayList();
      ObservableList<String> dataEmissao = FXCollections.observableArrayList();
      ObservableList<String> dataExpiracao = FXCollections.observableArrayList();
      ObservableList<String> delegado = FXCollections.observableArrayList();
      ObservableList<String> pdf = FXCollections.observableArrayList();
      ObservableList<String> votosFavor = FXCollections.observableArrayList();
      ObservableList<String> votosContra = FXCollections.observableArrayList();

      int startIndex = responseData.indexOf("<tbody>");
      int endIndex = responseData.indexOf("</tbody>");

      if (startIndex != -1 && endIndex != -1) {
        String tableBody = responseData.substring(startIndex, endIndex);

        // Split the table body into individual rows
        String[] rows = tableBody.split("</tr>");

        for (String row : rows) {
          //          System.out.println(row);
          String[] columnValues = row.split("<td>|</td>");
          List<String> nonEmptyValues = new ArrayList<>();
          for (String columnValue : columnValues) {
            columnValue = columnValue.trim();

            if (!columnValue.isEmpty()) {
              nonEmptyValues.add(columnValue);
            }
          }
          //          System.out.println(nonEmptyValues.toString());
          if (nonEmptyValues.size() == 9) {
            iD.add(nonEmptyValues.get(1));
            titulo.add(nonEmptyValues.get(2));
            dataEmissao.add(nonEmptyValues.get(3));
            dataExpiracao.add(nonEmptyValues.get(4));
            delegado.add(nonEmptyValues.get(5));
            pdf.add(nonEmptyValues.get(6));
            votosFavor.add(nonEmptyValues.get(7));
            votosContra.add(nonEmptyValues.get(8));
          }
        }
      }

      listView1.setItems(iD);
      listView2.setItems(titulo);
      listView3.setItems(dataEmissao);
      listView4.setItems(dataExpiracao);
      listView5.setItems(delegado);
      listView6.setItems(pdf);
      listView7.setItems(votosFavor);
      listView8.setItems(votosContra);
    } catch (Exception e) {
      e.printStackTrace();
    }
  }

  private void loaddata2() {
    try {
      String apiUrl = "http://localhost:8080/consultarProjetosLei";

      // Send the REST request
      URL url = new URL(apiUrl);
      HttpURLConnection connection = (HttpURLConnection) url.openConnection();
      connection.setRequestMethod("GET");

      // Read the response
      BufferedReader reader =
          new BufferedReader(new InputStreamReader(connection.getInputStream()));
      StringBuilder response = new StringBuilder();
      String line;
      while ((line = reader.readLine()) != null) {
        response.append(line);
      }
      reader.close();

      String responseData = response.toString();
      System.out.println(responseData);

      ObservableList<String> iD = FXCollections.observableArrayList();
      ObservableList<String> titulo = FXCollections.observableArrayList();
      ObservableList<String> dataEmissao = FXCollections.observableArrayList();
      ObservableList<String> dataExpiracao = FXCollections.observableArrayList();
      ObservableList<String> delegado = FXCollections.observableArrayList();
      ObservableList<String> pdf = FXCollections.observableArrayList();
      ObservableList<String> apoio = FXCollections.observableArrayList();
      ObservableList<String> textoDescritivo = FXCollections.observableArrayList();
      ObservableList<String> estadoDeExpiracao = FXCollections.observableArrayList();
      ObservableList<String> listadeApoiantes = FXCollections.observableArrayList();
      ObservableList<String> temas = FXCollections.observableArrayList();

      int startIndex = responseData.indexOf("<tbody>");
      int endIndex = responseData.indexOf("</tbody>");

      if (startIndex != -1 && endIndex != -1) {
        String tableBody = responseData.substring(startIndex, endIndex);

        // Split the table body into individual rows
        String[] rows = tableBody.split("</tr>");

        for (String row : rows) {
          //          System.out.println(row);
          String[] columnValues = row.split("<td>|</td>");
          List<String> nonEmptyValues = new ArrayList<>();
          for (String columnValue : columnValues) {
            columnValue = columnValue.trim();

            if (!columnValue.isEmpty()) {
              nonEmptyValues.add(columnValue);
            }
          }
          System.out.println(nonEmptyValues.toString());
          if (nonEmptyValues.size() == 12) {
            iD.add(nonEmptyValues.get(1));
            titulo.add(nonEmptyValues.get(2));
            pdf.add(nonEmptyValues.get(3));
            dataEmissao.add(nonEmptyValues.get(4));
            dataExpiracao.add(nonEmptyValues.get(5));
            apoio.add(nonEmptyValues.get(6));
            textoDescritivo.add(nonEmptyValues.get(7));
            estadoDeExpiracao.add(nonEmptyValues.get(8));
            listadeApoiantes.add(nonEmptyValues.get(9));
            delegado.add(nonEmptyValues.get(10));
            temas.add(nonEmptyValues.get(11));
          } else if (nonEmptyValues.size() == 11) {
            iD.add(nonEmptyValues.get(1));
            titulo.add(nonEmptyValues.get(2));
            pdf.add(nonEmptyValues.get(3));
            dataEmissao.add(nonEmptyValues.get(4));
            dataExpiracao.add(nonEmptyValues.get(5));
            apoio.add(nonEmptyValues.get(6));
            textoDescritivo.add(nonEmptyValues.get(7));
            estadoDeExpiracao.add(nonEmptyValues.get(8));
            listadeApoiantes.add("");
            delegado.add(nonEmptyValues.get(9));
            temas.add(nonEmptyValues.get(10));
          }
        }
      }

      cPlistView1.setItems(iD);
      cPlistView2.setItems(titulo);
      cPlistView3.setItems(pdf);
      cPlistView4.setItems(dataEmissao);
      cPlistView5.setItems(dataExpiracao);
      cPlistView6.setItems(apoio);
      cPlistView7.setItems(textoDescritivo);
      cPlistView8.setItems(estadoDeExpiracao);
      cPlistView9.setItems(listadeApoiantes);
      cPlistView10.setItems(delegado);
      cPlistView11.setItems(temas);
    } catch (Exception e) {
      e.printStackTrace();
    }
  }

  String prefix = "/static/css/presentation/view/";

  public void redirectToVotacoesEmCurso(ActionEvent event) {
    try {
      FXMLLoader loader = new FXMLLoader(getClass().getResource(prefix + "VotacoesEmCurso.fxml"));
      Parent root = loader.load();
      ClientController controller = loader.getController();
      controller.loaddata();
      Stage stage = (Stage) ((Button) event.getSource()).getScene().getWindow();
      stage.setScene(new Scene(root));
      stage.show();
    } catch (IOException e) {
      e.printStackTrace();
    }
  }

  public void redirectToApoiarProjeto(ActionEvent event) {
    try {
      FXMLLoader loader = new FXMLLoader(getClass().getResource(prefix + "ApoiarProjeto.fxml"));
      Parent root = loader.load();
      Stage stage = (Stage) ((Button) event.getSource()).getScene().getWindow();
      stage.setScene(new Scene(root));
      stage.show();
    } catch (IOException e) {
      e.printStackTrace();
    }
  }

  public void redirectToConsultarProjetosLei(ActionEvent event) {
    try {
      FXMLLoader loader =
          new FXMLLoader(getClass().getResource(prefix + "ConsultarProjetosLei.fxml"));
      Parent root = loader.load();
      ClientController controller = loader.getController();
      controller.loaddata2();
      Stage stage = (Stage) ((Button) event.getSource()).getScene().getWindow();
      stage.setScene(new Scene(root));
      stage.show();
    } catch (IOException e) {
      e.printStackTrace();
    }
  }

  public void redirectToVotarProposta(ActionEvent event) {
    try {
      FXMLLoader loader = new FXMLLoader(getClass().getResource(prefix + "VotarProposta.fxml"));
      Parent root = loader.load();
      Stage stage = (Stage) ((Button) event.getSource()).getScene().getWindow();
      stage.setScene(new Scene(root));
      stage.show();
    } catch (IOException e) {
      e.printStackTrace();
    }
  }

  public void redirectToVoltar(ActionEvent event) {
    try {
      FXMLLoader loader = new FXMLLoader(getClass().getResource(prefix + "MenuPrincipal.fxml"));
      Parent root = loader.load();
      Stage stage = (Stage) ((Button) event.getSource()).getScene().getWindow();
      stage.setScene(new Scene(root));
      stage.show();
    } catch (IOException e) {
      e.printStackTrace();
    }
  }
}
