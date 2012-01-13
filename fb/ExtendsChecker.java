import org.apache.bcel.classfile.Code;
import org.apache.bcel.classfile.Field;
import org.apache.bcel.classfile.JavaClass;
import org.apache.bcel.generic.ObjectType;

import edu.umd.cs.findbugs.BugInstance;
import edu.umd.cs.findbugs.BugReporter;
import edu.umd.cs.findbugs.BytecodeScanningDetector;
import edu.umd.cs.findbugs.ba.ObjectTypeFactory;

class ExtendsChecker extends BytecodeScanningDetector {

	public void visit(JavaClass someObj) {
		String currentClass = someObj.getClassName();
		System.out.println("Looking at " + currentClass);
		super.visit(someObj);
	}
}
