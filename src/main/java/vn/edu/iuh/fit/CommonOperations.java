package vn.edu.iuh.fit;

import java.io.File;
import java.util.regex.Pattern;

import com.github.javaparser.StaticJavaParser;
import com.github.javaparser.ast.PackageDeclaration;
import com.github.javaparser.ast.body.ClassOrInterfaceDeclaration;
import com.github.javaparser.ast.body.FieldDeclaration;
import com.github.javaparser.ast.body.MethodDeclaration;
import com.github.javaparser.ast.comments.JavadocComment;
import com.github.javaparser.ast.visitor.VoidVisitorAdapter;
import com.google.common.base.Strings;

public class CommonOperations {
	public static void listMethodCalls(File projectDir) {
		new DirExplorer((level, path, file) -> path.endsWith(".java"), (level, path, file) -> {
			System.out.println(path);
			System.out.println(Strings.repeat("=", path.length()));
			try {
				new VoidVisitorAdapter<Object>() {

					@Override
					public void visit(JavadocComment n, Object arg) {
						super.visit(n, arg);
						checkValidComments(n);
					}

					private void checkValidComments(JavadocComment n) {
						String content = n.getContent();
						if (content == null || content.isEmpty() || content == "") {
							System.out.println("\t********* Not valid comment");
						} else {
							System.out.println("[" + n.getBegin() + n.getContent() + "]" + n.getEnd());

							if (!isValidClassComment(content)) {
								System.out.println("\n********* Invalid Javadoc comment\n");
							} else {
								System.out.println("\n********* Valid Javadoc comment\n");
							}
						}
					}

					private boolean isValidClassComment(String content) {
						if (content == null || content.isEmpty()) {
							return false;
						}

						// Kiểm tra xem có "created-date" và "author" trong bình luận
						boolean hasCreatedDate = Pattern.compile("created-date", Pattern.CASE_INSENSITIVE)
								.matcher(content).find();
						boolean hasAuthor = Pattern.compile("author", Pattern.CASE_INSENSITIVE).matcher(content).find();

						return hasCreatedDate && hasAuthor;
					}

				}.visit(StaticJavaParser.parse(file), null);
			} catch (Exception e) {
				new RuntimeException(e);
			}
		}).explore(projectDir);
	}

	public static void main(String[] args) {
		String url = "C:\\Users\\CHAU\\Desktop\\HocTap\\Năm 3 Kỳ 2\\Phân Tán Java\\PhanTanCK\\Lab4Bai2_JPA";
		File projectDir = new File(url);
		listMethodCalls(projectDir);
	}
}
