package org.restcomm.connect.rvd.storage;

import org.restcomm.connect.rvd.model.project.Node;
import org.restcomm.connect.rvd.model.server.ProjectOptions;
import org.restcomm.connect.rvd.storage.exceptions.StorageException;

/**
 * @author otsakir@gmail.com - Orestis Tsakiridis
 */
public interface ProjectDao {
    ProjectOptions loadProjectOptions() throws StorageException;

    boolean hasBootstrapInfo();

    Node loadNode(String moduleName) throws StorageException;

    String loadBootstrapInfo() throws StorageException;
}