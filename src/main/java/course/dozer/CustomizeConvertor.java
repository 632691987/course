package course.dozer;

import org.dozer.DozerConverter;

public abstract class CustomizeConvertor<S, D> extends DozerConverter<S, D>{

	public CustomizeConvertor(Class<S> prototypeA, Class<D> prototypeB) {
		super(prototypeA, prototypeB);
	}

	@Override
	public D convertTo(S source, D destination) {
		return convertTo(source);
	}

	@Override
	public S convertFrom(D source, S destination) {
		return convertFrom(source);
	}
}
