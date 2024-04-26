package vn.edu.iuh.fit;

import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.io.BufferedReader;

import com.github.javaparser.StaticJavaParser;
import com.github.javaparser.ast.Node;
import com.github.javaparser.ast.NodeList;
import com.github.javaparser.ast.PackageDeclaration;
import com.github.javaparser.ast.body.ClassOrInterfaceDeclaration;
import com.github.javaparser.ast.body.ConstructorDeclaration;
import com.github.javaparser.ast.body.FieldDeclaration;
import com.github.javaparser.ast.body.MethodDeclaration;
import com.github.javaparser.ast.body.Parameter;
import com.github.javaparser.ast.body.VariableDeclarator;
import com.github.javaparser.ast.comments.Comment;
import com.github.javaparser.ast.comments.JavadocComment;
import com.github.javaparser.ast.visitor.VoidVisitorAdapter;
import com.google.common.base.Strings;

public class CommonOperations {
	// Đọc danh sách từ từ tệp
	public static List<String> readNounList(String filePath) throws IOException {
		List<String> words = new ArrayList<>();
		try (BufferedReader br = new BufferedReader(new FileReader(filePath))) {
			String line;
			while ((line = br.readLine()) != null) {
				words.add(line.trim());
			}
		}
		return words;
	}

	// Phương thức để lấy phần đầu trước phần viết hoa
	public static String getFirstPart(String name) {
		Pattern pattern = Pattern.compile("^[a-zA-Z]+");
		Matcher matcher = pattern.matcher(name);

		if (matcher.find()) {
			String firstMatch = matcher.group();
			int nextUpperIndex = getNextUpperIndex(firstMatch);
			return firstMatch.substring(0, nextUpperIndex).toLowerCase();
		} else {
			return "";
		}
	}

	// Tìm chỉ số của ký tự viết hoa tiếp theo
	private static int getNextUpperIndex(String name) {
		for (int i = 1; i < name.length(); i++) {
			if (Character.isUpperCase(name.charAt(i))) {
				return i;
			}
		}
		return name.length();
	}

	public static void listMethodCalls(File projectDir) {

		new DirExplorer((level, path, file) -> path.endsWith(".java"), (level, path, file) -> {
			System.out.println(path);
			System.out.println(Strings.repeat("=", path.length()));
			try {
				new VoidVisitorAdapter<Object>() {

					// Câu 1
//					@Override
//					public void visit(PackageDeclaration n, Object arg) {
//						super.visit(n, arg);
//						String packageName = n.getName().toString();
//						System.out.println("\npackageName: " + packageName);
//
//						if (!packageName.startsWith("com.companyname")) {
//							System.out.println("\n********* Invalid package: " + packageName + "\n");
//						}else {
//							System.out.println("\n********* Valid package: " + packageName + "\n");
//
//						}
//					}

					// Câu 2
//					@Override
//					public void visit(ClassOrInterfaceDeclaration n, Object arg) {
//						super.visit(n, arg);
//
//						List<String> nounList = new ArrayList<>();
//
//						try {
//							String nounlistPath = "nounlist.txt";
//
//							nounList = readNounList(nounlistPath);
//
//							String className = n.getNameAsString();
//							System.out.println("\nclassName: " + className);
//
//							String NOUN_PATTERN = "^[A-Z][a-zA-Z0-9]*(?:[A-Z][a-z0-9]*)*$";
//
//							if (Character.isUpperCase(className.charAt(0))
//									|| Pattern.matches(NOUN_PATTERN, className)) {
//								System.out.println("\n********* Valid class name " + className + "\n");
//							} else {
//								System.out.println("\n********* Invalid class name " + className + "\n");
//							}
//
//							String partName = getFirstPart(className);
//							System.out.println("\n********* Phần đầu đem ra so sánh trong nounlist: " + partName);
//
//							// Kiểm tra nếu tên lớp nằm trong danh sách từ nounlist
//							if (!nounList.contains(partName)) {
//								int index = nounList.indexOf(partName);
//								System.out.println("\n********* Invalid class name (in nounlist): " + index);
//							} else {
//								int index = nounList.indexOf(partName) + 1;
//								System.out.println("\n********* Valid class name (in nounlist): " + index);
//							}
//
//						} catch (IOException e) {
//							// TODO Auto-generated catch block
//							e.printStackTrace();
//						}
//
//					}

					// Câu 3

//					@Override
//					public void visit(JavadocComment n, Object arg) {
//						super.visit(n, arg);
//						checkValidComments(n);
//					}
//
//					private void checkValidComments(JavadocComment n) {
//						String content = n.getContent();
//						if (content == null || content.isEmpty() || content == "") {
//							System.out.println("\t********* Not valid comment");
//						} else {
//							System.out.println("[" + n.getBegin() + n.getContent() + "]" + n.getEnd());
//
//							if (!isValidClassComment(content)) {
//								System.out.println("\n********* Invalid Javadoc comment\n");
//							} else {
//								System.out.println("\n********* Valid Javadoc comment\n");
//							}
//						}
//					}
//
//					private boolean isValidClassComment(String content) {
//						if (content == null || content.isEmpty()) {
//							return false;
//						}
//
//						// Kiểm tra xem có "created-date" và "author" trong bình luận
//						boolean hasCreatedDate = Pattern.compile("created-date", Pattern.CASE_INSENSITIVE)
//								.matcher(content).find();
//						boolean hasAuthor = Pattern.compile("author", Pattern.CASE_INSENSITIVE).matcher(content).find();
//
//						return hasCreatedDate && hasAuthor;
//					}

					// Câu 4
//					@Override
//					public void visit(FieldDeclaration n, Object arg) {
//						super.visit(n, arg);
//
//						try {
//							String nounlistPath = "nounlist.txt";
//
//							List<String> nounList = readNounList(nounlistPath);
//
//							NodeList<VariableDeclarator> vars = n.getVariables();
//
//							vars.forEach(var -> {
//
//								char c = var.getNameAsString().charAt(0);
//								if ((c >= 'a' && c <= 'z')) {
//									System.out.println("\n********* Valid field " + var.getNameAsString());
//								} else {
//									System.out.println("\n********* Invalid field " + var.getNameAsString());
//								}
//
//								if (!nounList.contains(var.getNameAsString())) {
//									int index = nounList.indexOf(var.getNameAsString());
//									System.out.println("\n********* Invalid fied name " + var.getNameAsString()
//											+ " (in nounlist): " + index);
//								} else {
//									int index = nounList.indexOf(var.getNameAsString()) + 1;
//									System.out.println("\n********* Valid fied name " + var.getNameAsString()
//											+ " (in nounlist): " + index);
//								}
//
//							});
//						} catch (IOException e) {
//							// TODO Auto-generated catch block
//							e.printStackTrace();
//						}
//
//					}

					// Câu 6
					@Override
					public void visit(MethodDeclaration n, Object arg) {
						super.visit(n, arg);
						String verblistPath = "verbs.txt";

						try {
							List<String> verbs = readNounList(verblistPath);

							String methodName = n.getNameAsString();
							System.out.println("\nMethodName: " + methodName);

							if (!Character.isUpperCase(methodName.charAt(0))) {
								System.out.println("\n********* Valid method name " + methodName + "\n");
							} else {
								System.out.println("\n********* Invalid method name " + methodName + "\n");
							}

							String partName = getFirstPart(methodName);
							System.out.println("\n********* Phần đầu đem ra so sánh trong nounlist: " + partName);

							// Kiểm tra nếu tên lớp nằm trong danh sách từ nounlist
							if (!verbs.contains(partName)) {
								int index = verbs.indexOf(partName);
								System.out.println("\n********* Invalid class name (in nounlist): " + index);
							} else {
								int index = verbs.indexOf(partName) + 1;
								System.out.println("\n********* Valid class name (in nounlist): " + index);
							}
						} catch (IOException e) {
							// TODO Auto-generated catch block
							e.printStackTrace();
						}

					}

					// Câu 7
//					@Override
//					public void visit(MethodDeclaration n, Object arg) {
//						super.visit(n, arg);
//						String methodName = n.getNameAsString();
//
//						// Phương thức default constructor, accessors/mutators,
//						// hashCode, equals, toString
//						if (isCommonMethod(methodName)) {
//							System.out.println("\n********* Method default " + methodName);
//						}
//
//						// Kiểm tra nếu không có comment hoặc comment là rỗng
//						if (!hasValidComment(n)) {
//							System.out.println("\n********* Method invalid comment " + methodName);
//						} else {
//							System.out.println("\n********* Method valid comment " + methodName);
//
//						}
//
//					}
//
//					@Override
//					public void visit(ConstructorDeclaration n, Object arg) {
//						super.visit(n, arg);
//
//						// Lấy danh sách các tham số
//						List<Parameter> parameters = n.getParameters();
//
//						// Hiển thị tên của constructor
//						System.out.println("\nConstructor: " + n.getDeclarationAsString());
//
//						if (parameters.isEmpty()) {
//							System.out.println("This is a default constructor (no parameters).");
//						} else {
//							// Kiểm tra nếu không có comment hoặc comment là rỗng
//							if (!hasValidComment(n)) {
//								System.out.println(
//										"Constructor invalid comment " + n.getDeclarationAsString());
//							} else {
//								System.out
//										.println("Constructor valid comment " + n.getDeclarationAsString());
//
//							}
//						}
//
//					}
//
//					private boolean hasValidComment(ConstructorDeclaration n) {
//						Comment comment = n.getComment().orElse(null);
//						return comment != null && !comment.getContent().trim().isEmpty();
//					}
//
//					private boolean isCommonMethod(String methodName) {
//						return methodName.equals("hashCode") || methodName.equals("equals")
//								|| methodName.equals("toString");
//					}
//
//					private boolean hasValidComment(MethodDeclaration md) {
//						Comment comment = md.getComment().orElse(null);
//						return comment != null && !comment.getContent().trim().isEmpty();
//					}

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
