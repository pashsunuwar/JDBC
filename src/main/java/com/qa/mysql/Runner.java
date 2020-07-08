package com.qa.mysql;

public class Runner {

	public static void main(String[] args) {
		LearningJDBC db = new LearningJDBC("root", "root");

		db.OpeningTheConnecton();
		db.Create();
		db.Read();
		db.Update();
		db.Delete();

	}
}