package edu.uob;

class OXOController {
  OXOModel gameModel;

  public OXOController(OXOModel model) {
    gameModel = model;
  }

  public void handleIncomingCommand(String command) throws OXOMoveException {}
  public void addRow() {}
  public void removeRow() {}
  public void addColumn() {}
  public void removeColumn() {}
  public void increaseWinThreshold() {}
  public void decreaseWinThreshold() {}
}
