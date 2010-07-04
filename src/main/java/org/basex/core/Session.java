package org.basex.core;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import org.basex.io.CachedOutput;

/**
 * This class allows a generic command execution.
 * It is implemented both by the local as well as the client/server model.
 *
 * @author Workgroup DBIS, University of Konstanz 2005-10, ISC License
 * @author Christian Gruen
 */
public abstract class Session {
  /** Command info. */
  protected String info;

  /**
   * Executes a command and prints the result to the specified stream.
   * @param cmd command to be parsed
   * @param out output stream
   * @throws BaseXException database exception
   */
  public abstract void execute(final String cmd, final OutputStream out)
    throws BaseXException;

  /**
   * Executes a command and prints the result to the specified stream.
   * @param cmd command to be executed
   * @param out output stream
   * @throws BaseXException database exception
   */
  public abstract void execute(final Command cmd, final OutputStream out)
    throws BaseXException;

  /**
   * Executes a command and returns the result as string.
   * @param cmd command to be executed
   * @return result
   * @throws BaseXException database exception
   */
  public final String execute(final Command cmd) throws BaseXException {
    final CachedOutput out = new CachedOutput();
    execute(cmd, out);
    return out.toString();
  }

  /**
   * Executes a command and returns the result as string.
   * @param cmd command to be parsed
   * @return result
   * @throws BaseXException database exception
   */
  public final String execute(final String cmd) throws BaseXException {
    final CachedOutput out = new CachedOutput();
    execute(cmd, out);
    return out.toString();
  }

  /**
   * Creates a database.
   * @param name name of database
   * @param input xml input
   * @throws BaseXException database exception
   */
  public abstract void create(final String name, final InputStream input)
    throws BaseXException;

  /**
   * Returns command info.
   * @return command info
   */
  public final String info() {
    return info;
  }

  /**
   * Closes the session.
   * @throws IOException I/O exception
   */
  public abstract void close() throws IOException;
}
