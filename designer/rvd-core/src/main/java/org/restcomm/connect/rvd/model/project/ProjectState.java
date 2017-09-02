package org.restcomm.connect.rvd.model.project;

import java.util.List;


public class ProjectState {

    Integer lastStepId;
    List<Node> nodes;
    Integer activeNode;
    Integer lastNodeId;
    StateHeader header;
    ExceptionHandlingInfo exceptionHandlingInfo;


    public ProjectState() {
        super();
    }

    public Integer getLastStepId() {
        return lastStepId;
    }

    public void setLastStepId(Integer lastStepId) {
        this.lastStepId = lastStepId;
    }

    public List<Node> getNodes() {
        return nodes;
    }

    public void setNodes(List<Node> nodes) {
        this.nodes = nodes;
    }

    public Integer getActiveNode() {
        return activeNode;
    }

    public void setActiveNode(Integer activeNode) {
        this.activeNode = activeNode;
    }

    public Integer getLastNodeId() {
        return lastNodeId;
    }

    public void setLastNodeId(Integer lastNodeId) {
        this.lastNodeId = lastNodeId;
    }

    public StateHeader getHeader() {
        return header;
    }

    public void setHeader(StateHeader header) {
        this.header = header;
    }

    public ExceptionHandlingInfo getExceptionHandlingInfo() {
        return exceptionHandlingInfo;
    }



}
