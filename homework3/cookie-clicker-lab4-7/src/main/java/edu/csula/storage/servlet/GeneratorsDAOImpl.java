package edu.csula.storage.servlet;

import java.util.Collection;
import java.util.List;
import java.util.ArrayList;
import java.util.Optional;

import javax.servlet.ServletContext;

import edu.csula.storage.GeneratorsDAO;
import edu.csula.models.Generator;

/**
 * To abstract the storage access from the business layer using ServletContext
 * (application scope). This implementation will store the underlying data under
 * the application scope and read from it accordingly.
 *
 * As ServletContext is like a global HashMap, it's important for you to add a
 * context name to separate out the different section of data (e.g. events vs
 * generators) so that you can have the application scope looks like below:
 *
 * ```json
 * {
 *   "events": [
 *     { "id": 0, "name": "event-1", "description": "..." }
 *   ],
 *   "generators": [
 *     { "id": 0, "name": "generator-1", "description": "..." }
 *   ]
 * }
 * ```
 */
public class GeneratorsDAOImpl implements GeneratorsDAO {
	private final ServletContext context;
	protected static final String CONTEXT_NAME = "generators";

	public GeneratorsDAOImpl(ServletContext context) {
		this.context = context;
	}

	@Override
	public List<Generator> getAll() {
		// TODO: get a list of generators from the context
		List<Generator> generators=(List<Generator>) context.getAttribute(CONTEXT_NAME);

		if(generators != null){
			return generators;
		}
		else {
            return new ArrayList<>();
        }
	}

	@Override
	public Optional<Generator> getById(int id) {
		// TODO: get a certain generator from context
        List<Generator> generators=(List<Generator>) context.getAttribute(CONTEXT_NAME);
        Generator g;
        for(int i=0;i<generators.size();i++){
            if(generators.get(i).getId()==id){
                g=generators.get(i);
                return Optional.of(g);
            }
        }
		return Optional.empty();
	}

	@Override
	public void set(int id, Generator generator) {
		// TODO: change a certain generator from context
        List<Generator> generators=(List<Generator>) context.getAttribute(CONTEXT_NAME);
        if(generators!=null) {
            for(Generator g:generators){
                if(g.getId()==id){
                    g.setId(generator.getId());
                    g.setName(generator.getName());
                    g.setDescription(generator.getDescription());
                    g.setRate(generator.getRate());
                    g.setBaseCost(generator.getBaseCost());
                    g.setUnlockAt(generator.getUnlockAt());
                }
            }
            context.setAttribute(CONTEXT_NAME, generators);
        }
        else{
            List<Generator> generators1=new ArrayList<>();
            generators=generators1;
            context.setAttribute(CONTEXT_NAME, generators);
        }
	}

	@Override
	public void add(Generator generator) {
		// TODO: add a new generator to the context
        List<Generator> generators=(List<Generator>) context.getAttribute(CONTEXT_NAME);
        if(generators!=null) {
            generators.add(generator);
            context.setAttribute(CONTEXT_NAME, generators);
        }
        else{
            List<Generator> generators1=new ArrayList<>();
            generators1.add(generator);
            generators=generators1;
            context.setAttribute(CONTEXT_NAME, generators);
        }
	}

	@Override
	public void remove(int id) {
		// TODO: remove a single generator from the context
        List<Generator> generators=(List<Generator>) context.getAttribute(CONTEXT_NAME);
        if(generators!=null){
            for(Generator g:generators){
                if(g.getId()==id){
                    generators.remove(g);
                    break;
                }
            }
            context.setAttribute(CONTEXT_NAME,generators);
        }
        else{
            List<Generator> generators1=new ArrayList<>();
            generators=generators1;
            context.setAttribute(CONTEXT_NAME, generators);
        }
	}
}
