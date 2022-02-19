package db;

import java.io.FileInputStream;
import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Properties;

public class DB {

	private static Connection conn = null;

	// Estabelecer uma conexão
	public static Connection getConnection() {
		if (conn == null) {
			try {
				Properties props = loadProperties();
				String url = props.getProperty("dburl");        // url estabelecida no db.properties
				conn = DriverManager.getConnection(url, props); // variável conn recebe a url do banco e suas
																// propiedades
			} catch (SQLException ex) {
				throw new DbException(ex.getMessage());
			}
		}
		return conn;
	}

	// Fechar uma conexão
	public static void fechaConexao() {
		if (conn != null) {
			try {
				conn.close();
			} catch (SQLException ex) {
				throw new DbException(ex.getMessage());
			}
		}
	}

	// Abrir o arquivo db.properties, ler os dados e guardar no objeto Properties
	private static Properties loadProperties() {
		try (FileInputStream fis = new FileInputStream("db.properties")) {
			Properties props = new Properties();
			props.load(fis);     // Leitura do arquivo db.properties e guarda os dados dentro de props
			return props;
		} catch (IOException ex) {
			throw new DbException(ex.getMessage());
		}
	}

	// Tratar o close da variável stat
	public static void fechaStatement(Statement stat) {
		if (stat != null) {
			try {
				stat.close();
			} catch (SQLException ex) {
				throw new DbException(ex.getMessage());
			}
		}
	}

	// Tratar o close da variável res
	public static void fechaResultSet(ResultSet res) {
		if (res != null) {
			try {
				res.close();
			} catch (SQLException ex) {
				throw new DbException(ex.getMessage());
			}
		}
	}
}
