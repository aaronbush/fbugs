import org.apache.bcel.classfile.Code;
import org.apache.bcel.classfile.Field;
import org.apache.bcel.classfile.JavaClass;
import org.apache.bcel.generic.ObjectType;

import edu.umd.cs.findbugs.BugInstance;
import edu.umd.cs.findbugs.BugReporter;
import edu.umd.cs.findbugs.BytecodeScanningDetector;
import edu.umd.cs.findbugs.StatelessDetector;
import edu.umd.cs.findbugs.ba.ObjectTypeFactory;

public class ExtendsChecker extends BytecodeScanningDetector {

	private static final String badClass = "Forbidden";
	private BugReporter bugReporter;
	private boolean sawBadClass = false;

	public ExtendsChecker(BugReporter bugReporter) {
		this.bugReporter = bugReporter;
		System.out.println("DEBUG:Reporter: " + bugReporter);
	}

	@Override
	public void sawOpcode(int seen) {
		if (sawBadClass && seen == ALOAD_0) {
			bugReporter.reportBug(new BugInstance(this, "EXTENDS_BUG", NORMAL_PRIORITY).addClassAndMethod(this).addString("oops").addSourceLine(this));
		}
	}

	@Override
	public void visit(JavaClass someObj) {
		String currentClass = someObj.getClassName();
		System.out.println("DEBUG:Looking at " + currentClass);
		if (currentClass.equals(badClass)) {
			sawBadClass = true;
		} else {
			sawBadClass = false;
		}
	}
}
