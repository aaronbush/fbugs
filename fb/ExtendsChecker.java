import org.apache.bcel.classfile.Code;
import org.apache.bcel.classfile.Field;
import org.apache.bcel.classfile.JavaClass;
import org.apache.bcel.generic.ObjectType;

import edu.umd.cs.findbugs.BugInstance;
import edu.umd.cs.findbugs.BugReporter;
import edu.umd.cs.findbugs.BytecodeScanningDetector;
import edu.umd.cs.findbugs.StatelessDetector;
import edu.umd.cs.findbugs.ba.ObjectTypeFactory;

public class ExtendsChecker extends BytecodeScanningDetector implements StatelessDetector {

	private static final String badClass = "Forbidden";
	private BugReporter bugReporter;

	public ExtendsChecker(BugReporter bugReporter) {
		this.bugReporter = bugReporter;
		System.out.println("DEBUG:Reporter: " + bugReporter);
	}

	@Override
	public void visit(JavaClass someObj) {
		String currentClass = someObj.getClassName();
		System.out.println("DEBUG:Looking at " + currentClass);
		if (currentClass.equals(badClass)) {
			bugReporter.reportBug(new BugInstance("EXTENDS_BUG", HIGH_PRIORITY));
		}
		//super.visit(someObj);
	}
}
