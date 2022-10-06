package ui.pantallas.reader.listType;

import io.github.palexdev.materialfx.controls.MFXTextField;
import javafx.event.ActionEvent;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import ui.pantallas.common.BasePantallaController;

public class ReaderListTypeController extends BasePantallaController {
    public TableView readersTable;
    public TableColumn idColumn;
    public TableColumn nameColum;
    public TableColumn dateColumn;
    public MFXTextField idNewspaper;

    public void search(ActionEvent actionEvent) {
    }
}

