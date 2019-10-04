package es.envconf.app.controller;

import es.envconf.app.model.applicationServer.jboss.JBossVersion;
import es.envconf.app.model.dto.EnvironmentDTO;
import es.envconf.app.model.service.EnvironmentsService;
import es.envconf.app.model.service.exception.EnvironmentException;
import es.envconf.app.model.service.impl.EnvironmentsServiceImpl;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.text.Text;

import java.util.ArrayList;
import java.util.List;

public class MainController {

    @FXML
    private Text actiontarget;

    @FXML protected void handleSubmitButtonAction(ActionEvent event) {
        actiontarget.setText("... Aqui comienza todo....");
    }

    @FXML
    private Label lbEntorno, lbID, lbRama, lbApp, lbDebug, lbUserBD, lbPass, lbHost, lbPortBD, lbServName;

    @FXML
    private ListView<EnvironmentDTO> listEnvironments;

    @FXML
    private ListView<String> listApplicationServers;

    private List<EnvironmentDTO> listEnvironmentDTO;

    public void init(){
        try {
            EnvironmentsService environmentsService = new EnvironmentsServiceImpl();
            List<EnvironmentDTO> environmentsDTO = environmentsService.getEnvironments();

            if( environmentsDTO != null && environmentsDTO.size() > 0 ){
                listEnvironmentDTO = new ArrayList<>();
                listEnvironmentDTO.addAll(environmentsDTO);
                _setearInformacionEntorno(environmentsDTO.get(0));
                ObservableList<EnvironmentDTO> itemsEnvironments = FXCollections.observableArrayList();
                itemsEnvironments.setAll(environmentsDTO);
                listEnvironments.setItems(itemsEnvironments);
            }

            List<String> jbossVersionNames = JBossVersion.getVersionNames();
            ObservableList<String> itemsAppServer = FXCollections.observableArrayList();
            itemsAppServer.setAll(jbossVersionNames);
            listApplicationServers.setItems(itemsAppServer);

            defineEventListener();
        } catch (EnvironmentException e) {
            e.printStackTrace();
        }
    }


    private void _setearInformacionEntorno(EnvironmentDTO entorno){
        lbEntorno.setText   (entorno.getProject());
        lbRama.setText      (entorno.getBranch());
        lbApp.setText       (entorno.getPortApp());
        lbDebug.setText     (entorno.getPortDebug());
        lbUserBD.setText    (entorno.getUserbd());
        lbPass.setText      (entorno.getPassbd());
        lbHost.setText      (entorno.getHost());
        lbPortBD.setText    (entorno.getPortBD());
        lbServName.setText  (entorno.getServiceName());
    }

    private void defineEventListener(){
        listEnvironments.getSelectionModel().selectedItemProperty()
                .addListener(new ChangeListener<EnvironmentDTO>() {
                    public void changed(ObservableValue<? extends EnvironmentDTO> ov,
                                        EnvironmentDTO old_val, EnvironmentDTO new_val) {
                        _setearInformacionEntorno(new_val);
                    }
                });
    }
}
