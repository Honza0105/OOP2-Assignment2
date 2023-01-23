//
// While food can be very complex, here we treat it as if it has no structure
// and all animal can eat all foods. The only special thing about these foods
// is that they have a filling value, which can be any non-negative int.
//
public interface IFood {
	int getFillingValue();
}
